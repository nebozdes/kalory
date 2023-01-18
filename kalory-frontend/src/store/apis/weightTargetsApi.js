import { createApi, fetchBaseQuery } from "@reduxjs/toolkit/query/react";
import { API_URL } from "./apiConstants";

const weightTargetsApi = createApi({
  reducerPath: "weightTargets",
  baseQuery: fetchBaseQuery({
    baseUrl: `${API_URL}/v1`,
    credentials: "include",
  }),
  endpoints(builder) {
    return {
      fetchWeightTargets: builder.query({
        providesTags: ["WeightTargets"],
        query: (page = 0, limit = 10) => {
          return {
            url: "/weight-target",
            method: "GET",
            params: {
              page,
              limit,
            },
          };
        },
      }),
      createWeightTarget: builder.mutation({
        invalidatesTags: ["WeightTargets"],
        query: (newObject) => {
          return {
            url: "/weight-target",
            body: newObject,
            method: "POST",
          };
        },
      }),
      deleteWeightTarget: builder.mutation({
        invalidatesTags: ["WeightTargets"],
        query: (weightTarget) => {
          return {
            url: `/weight-target/${weightTarget.id}`,
            method: "DELETE",
          };
        },
      }),
    };
  },
});

export const {
  useFetchWeightTargetsQuery,
  useCreateWeightTargetMutation,
  useUpdateWeightTargetMutation,
  useDeleteWeightTargetMutation,
} = weightTargetsApi;
export { weightTargetsApi };
