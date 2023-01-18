import { createApi, fetchBaseQuery } from "@reduxjs/toolkit/query/react";
import { API_URL } from "./apiConstants";

const productsApi = createApi({
  reducerPath: "products",
  baseQuery: fetchBaseQuery({
    baseUrl: `${API_URL}/v1`,
    credentials: "include",
  }),
  endpoints(builder) {
    return {
      fetchProducts: builder.query({
        providesTags: ["Products"],
        query: ({ page = 0, limit = 10 }) => {
          return {
            url: "/product",
            method: "GET",
            params: {
              page,
              limit,
            },
          };
        },
      }),
      createProduct: builder.mutation({
        invalidatesTags: ["Products"],
        query: (newProduct) => {
          return {
            url: "/product",
            body: newProduct,
            method: "POST",
          };
        },
      }),
      deleteProduct: builder.mutation({
        invalidatesTags: ["Products"],
        query: (product) => {
          return {
            url: `/product/${product.id}`,
            method: "DELETE",
          };
        },
      }),
    };
  },
});

export const {
  useFetchProductsQuery,
  useCreateProductMutation,
  useDeleteProductMutation,
} = productsApi;
export { productsApi };
