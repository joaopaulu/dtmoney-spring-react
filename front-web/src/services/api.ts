import axios, { AxiosRequestConfig } from 'axios';

const BASE_URL = process.env.REACT_APP_BACKEND_URL ?? 'http://localhost:8080';

export const api = (params: AxiosRequestConfig) => {
  return axios({
    ...params,
    baseURL: BASE_URL,
  });
};
