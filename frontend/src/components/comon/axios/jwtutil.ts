import { jwtDecode } from "jwt-decode";

export function isTokenExpired(token: string): boolean {
  try {
    const decoded = jwtDecode<{ exp: number }>(token);
    return decoded.exp * 1000 < Date.now(); // exp는 초 단위
  } catch {
    return true; // decode 실패 시 만료로 처리
  }
}
