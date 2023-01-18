import { createApi, fetchBaseQuery } from "@reduxjs/toolkit/query/react";
import { API_URL } from "./apiConstants";

const tagsApi = createApi({
  reducerPath: "tags",
  baseQuery: fetchBaseQuery({
    baseUrl: `${API_URL}/v1`,
    credentials: "include",
  }),
  endpoints(builder) {
    return {
      fetchTags: builder.query({
        providesTags: ["Tags"],
        query: ({ page = 0, limit = 10 }) => {
          return {
            url: "/tag",
            method: "GET",
            params: {
              page,
              limit,
            },
          };
        },
      }),
      createTag: builder.mutation({
        invalidatesTags: ["Tags"],
        query: (newTag) => {
          return {
            url: "/tag",
            body: newTag,
            method: "POST",
          };
        },
      }),
      deleteTag: builder.mutation({
        invalidatesTags: ["Tags"],
        query: (tag) => {
          return {
            url: `/tag/${tag.id}`,
            method: "DELETE",
          };
        },
      }),
    };
  },
});

export const { useFetchTagsQuery, useCreateTagMutation, useDeleteTagMutation } =
  tagsApi;
export { tagsApi };
