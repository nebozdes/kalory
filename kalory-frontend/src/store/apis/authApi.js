import { createApi, fetchBaseQuery } from "@reduxjs/toolkit/query/react";
import { API_URL } from "./apiConstants";

const authApi = createApi({
  reducerPath: "auth",
  baseQuery: fetchBaseQuery({
    baseUrl: `${API_URL}/v1`,
    credentials: "include",
    headers: {
      Accept: "application/json",
    },
  }),

  endpoints(builder) {
    return {
      getCurrentUser: builder.query({
        providesTags: ["CurrentUser"],
        query: () => {
          return {
            url: "/user/me",
            method: "GET",
          };
        },
      }),
      login: builder.mutation({
        invalidatesTags: () => {
          return ["CurrentUser"];
        },
        query: ({ username, password }) => {
          return {
            url: "/login",
            mode: "no-cors",
            body: {
              username: username,
              password: password,
            },
            method: "POST",
          };
        },
      }),
      logout: builder.mutation({
        invalidatesTags: () => {
          return ["CurrentUser"];
        },
        query: () => {
          return {
            url: "/logout",
            mode: "no-cors",
            method: "POST",
          };
        },
      }),
    };
  },
});

export const { useGetCurrentUserQuery, useLoginMutation, useLogoutMutation } =
  authApi;
export { authApi };
