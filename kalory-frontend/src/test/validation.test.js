import {
  notEmpty,
  notNull,
  positive,
  positiveOrZero,
} from "../components/forms/validation";

it("notNull should return false on null and undefined values", () => {
  expect(notNull(null)).toBe(false);
  expect(notNull(undefined)).toBe(false);
});

it("notNull should return true on not empty strings, numbers, objects", () => {
  expect(notNull(123)).toBe(true);
  expect(notNull("")).toBe(true);
  expect(notNull({})).toBe(true);
  expect(notNull("someString")).toBe(true);
});

it("notEmpty should return false on null, undefined or empty string values", () => {
  expect(notEmpty(null)).toBe(false);
  expect(notEmpty(undefined)).toBe(false);
  expect(notEmpty("")).toBe(false);
});

it("notEmpty should return true on not empty strings", () => {
  expect(notEmpty("someString")).toBe(true);
});

it("positive should return true on positive number", () => {
  expect(positive(1)).toBe(true);
  expect(positive(123)).toBe(true);
  expect(positive(0.04)).toBe(true);
});

it("positive should return false on negative or zero number", () => {
  expect(positive(0)).toBe(false);
  expect(positive(-0.04)).toBe(false);
  expect(positive(-123)).toBe(false);
});

it("positiveOrZero should return true on positive number or zero", () => {
  expect(positiveOrZero(1)).toBe(true);
  expect(positiveOrZero(123)).toBe(true);
  expect(positiveOrZero(0.04)).toBe(true);
  expect(positiveOrZero(0)).toBe(true);
});

it("positiveOrZero should return false on negative or zero number", () => {
  expect(positiveOrZero(-0.04)).toBe(false);
  expect(positiveOrZero(-123)).toBe(false);
});
