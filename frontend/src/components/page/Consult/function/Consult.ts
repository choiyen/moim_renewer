import { GET } from "../../../comon/axios/axiosInstance";

export const ConsultCategorying = async (ConsultCategory: number) => {
  try {
    const res = await GET({
      url: "/consult/all",
      params: { MoimCategoryId: ConsultCategory },
    });

    console.log(res.data);
    return res.data ?? [];
  } catch (err: unknown) {
    // err가 object인지 확인 후 response 접근
    if (typeof err === "object" && err !== null && "response" in err) {
      const maybeAxiosError = err as {
        response?: { data?: { resultType?: string } };
      };

      if (maybeAxiosError.response?.data?.resultType === "empty") {
        console.log(maybeAxiosError.response);
        return [];
      }
    }

    // 일반 에러 처리
    if (err instanceof Error) {
      console.error(err.message);
    }

    return [];
  }
};
