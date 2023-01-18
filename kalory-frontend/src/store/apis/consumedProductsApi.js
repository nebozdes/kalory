import { createApi, fetchBaseQuery } from "@reduxjs/toolkit/query/react";
import { API_URL } from "./apiConstants";

const consumedProductsApi = createApi({
  reducerPath: "consumedProducts",
  baseQuery: fetchBaseQuery({
    baseUrl: `${API_URL}/v1`,
    credentials: "include",
  }),
  endpoints(builder) {
    return {
      fetchConsumedProducts: builder.query({
        providesTags: ["ConsumedProducts"],
        query: (consumptionDate) => {
          return {
            url: "/consumed-product",
            method: "GET",
            params: {
              page: 0,
              limit: 10,
              consumptionDate,
            },
          };
        },
      }),
      createConsumedProduct: builder.mutation({
        invalidatesTags: ["ConsumedProducts"],
        query: (newState) => {
          return {
            url: "/consumed-product",
            body: newState,
            method: "POST",
          };
        },
      }),
      deleteConsumedProduct: builder.mutation({
        invalidatesTags: ["ConsumedProducts"],
        query: (consumedProduct) => {
          return {
            url: `/consumed-product/${consumedProduct.id}`,
            method: "DELETE",
          };
        },
      }),
    };
  },
});

export const {
  useFetchConsumedProductsQuery,
  useCreateConsumedProductMutation,
  useDeleteConsumedProductMutation,
} = consumedProductsApi;
export { consumedProductsApi };
