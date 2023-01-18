import { createApi, fetchBaseQuery } from "@reduxjs/toolkit/query/react";
import { API_URL } from "./apiConstants";

const weightChecksApi = createApi({
  reducerPath: "weightChecks",
  baseQuery: fetchBaseQuery({
    baseUrl: `${API_URL}/v1`,
    credentials: "include",
  }),
  endpoints(builder) {
    return {
      fetchWeightChecks: builder.query({
        providesTags: ["WeightChecks"],
        query: (page = 0, limit = 10) => {
          return {
            url: "/weight-check",
            method: "GET",
            params: {
              page,
              limit,
            },
          };
        },
      }),
      createWeightCheck: builder.mutation({
        invalidatesTags: ["WeightChecks"],
        query: (newObject) => {
          return {
            url: "/weight-check",
            body: newObject,
            method: "POST",
          };
        },
      }),
      deleteWeightCheck: builder.mutation({
        invalidatesTags: ["WeightChecks"],
        query: (weightCheck) => {
          return {
            url: `/weight-check/${weightCheck.id}`,
            method: "DELETE",
          };
        },
      }),
    };
  },
});

export const {
  useFetchWeightChecksQuery,
  useCreateWeightCheckMutation,
  useDeleteWeightCheckMutation,
} = weightChecksApi;
export { weightChecksApi };
