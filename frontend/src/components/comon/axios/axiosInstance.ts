import axios, { AxiosError } from "axios";
import type {
  AxiosRequestConfig,
  AxiosResponse,
  Method,
  InternalAxiosRequestConfig,
} from "axios";
import { baseURL, timeout } from "./util";
import { toast } from "react-toastify";

const TIMEOUT = timeout ? parseInt(timeout, 10) : undefined;

const requestInterceptor = {
  onFulfilled: (config: InternalAxiosRequestConfig) => {
    // withCredentials는 인스턴스 생성 시 설정했으니 보통 여기선 안 해도 됨
    return config;
  },
  onRejected: (error: AxiosError) => Promise.reject(error),
};

const responseInterceptor = {
  onFulfilled: (response: AxiosResponse) => {
    const contentType = response.headers["content-type"]?.toString();
    const isJson = contentType?.includes("application/json");

    if (isJson) {
      try {
        if (typeof response.data === "string") {
          response.data = JSON.parse(response.data);
        } else if (typeof response.data !== "object") {
          throw new Error("Invalid JSON response");
        }
      } catch {
        throw new Error("AXIOS Parse ERROR");
      }
    }

    if (
      typeof response.data === "string" &&
      response.data.includes("<!doctype html>")
    ) {
      //html 파일로 데이터가 넘어오면 axios 에러
      throw new AxiosError("Not Found", "404", undefined, undefined, response);
    }

    if (response.status >= 400) {
      const code = response.data?.code || response.statusText;
      const messageInConfig =
        response.data.message || response.data?.error?.message;

      const message =
        typeof messageInConfig === "string"
          ? messageInConfig
          : Array.isArray(messageInConfig)
          ? messageInConfig.find((v) => v) || response.statusText
          : response.statusText || messageInConfig;

      throw new AxiosError(message, code, undefined, undefined, response);
    }

    return response;
  },
  onRejected: async (error: AxiosError) => {
    const { response } = error;

    if (!response) {
      //응답 자체가 없다면 서버로의 수신이 안되는 것이므로 toast 출력
      toast.error(
        "서버와의 연결에 문제가 발생했습니다. 인터넷 연결을 확인해주세요.",
        {
          position: "top-center",
          autoClose: 5000,
        }
      );
      return Promise.reject(error);
    }

    if (response.status === 401) {
      //로그인이 안되어 있다면 프론트엔드의 로그인 페이지로 리다이렉트
      window.location.href = "/login";
      return;
    }

    return Promise.reject(error); //다른 오류라면 그대로
  },
};

const axiosInstance = axios.create({
  baseURL,
  timeout: TIMEOUT,
  withCredentials: true,
});

axiosInstance.interceptors.request.use(
  requestInterceptor.onFulfilled,
  requestInterceptor.onRejected
);

axiosInstance.interceptors.response.use(
  responseInterceptor.onFulfilled,
  responseInterceptor.onRejected
);

const curryingMethod =
  (method: Method) =>
  async (requestConfig: Omit<AxiosRequestConfig, "method">) => {
    return axiosInstance
      .request({ ...requestConfig, method })
      .then((response) => response.data);
  };

export const GET = curryingMethod("get");
export const POST = curryingMethod("post");
export const PUT = curryingMethod("put");
export const DELETE = curryingMethod("delete");
export const OPTIONS = curryingMethod("options");
export const PATCH = curryingMethod("patch");

export default axiosInstance;
