import { configureStore } from "@reduxjs/toolkit";
import { setupListeners } from "@reduxjs/toolkit/query";
import { authApi } from "./apis/authApi";
import { consumedProductsApi } from "./apis/consumedProductsApi";
import { productsApi } from "./apis/productsApi";
import { tagsApi } from "./apis/tagsApi";
import { weightChecksApi } from "./apis/weightChecksApi";
import { weightTargetsApi } from "./apis/weightTargetsApi";

export const store = configureStore({
  reducer: {
    auth: authApi.reducer,
    products: productsApi.reducer,
    consumedProducts: consumedProductsApi.reducer,
    tags: tagsApi.reducer,
    weightChecks: weightChecksApi.reducer,
    weightTargets: weightTargetsApi.reducer,
  },
  middleware: (getDefaultMiddleware) => {
    return getDefaultMiddleware()
      .concat(authApi.middleware)
      .concat(productsApi.middleware)
      .concat(consumedProductsApi.middleware)
      .concat(tagsApi.middleware)
      .concat(weightChecksApi.middleware)
      .concat(weightTargetsApi.middleware);
  },
});

setupListeners(store.dispatch);

export {
  useGetCurrentUserQuery,
  useLoginMutation,
  useLogoutMutation,
} from "./apis/authApi";
export {
  useFetchProductsQuery,
  useCreateProductMutation,
  useDeleteProductMutation,
} from "./apis/productsApi";
export {
  useFetchConsumedProductsQuery,
  useCreateConsumedProductMutation,
  useDeleteConsumedProductMutation,
} from "./apis/consumedProductsApi";
export {
  useFetchTagsQuery,
  useCreateTagMutation,
  useDeleteTagMutation,
} from "./apis/tagsApi";
export {
  useFetchWeightChecksQuery,
  useCreateWeightCheckMutation,
  useDeleteWeightCheckMutation,
} from "./apis/weightChecksApi";
export {
  useFetchWeightTargetsQuery,
  useCreateWeightTargetMutation,
  useDeleteWeightTargetMutation,
} from "./apis/weightTargetsApi";
