/*
 * Config is represented by array of field definitions: key and field validation function
 * Overall form validation function returns the list of the field which should be highlighted as incorrect
 */
const validateForm = (config, formValues) => {
  return config
    .filter((fieldConfig) => {
      const key = fieldConfig.key;
      const validationFunction = fieldConfig.vFn;

      const validatedValue = formValues[key];

      if (!validatedValue) {
        return true;
      }

      const fieldValidationResult = validationFunction(validatedValue);

      if (!fieldValidationResult) {
        return true;
      }
      return false;
    })
    .map((fieldConfig) => fieldConfig.key);
};

const notNull = (param) => {
  return param !== undefined && param !== null;
};

const notEmpty = (param) => {
  return notNull(param) && param.toString().length > 0;
};

const positive = (param) => {
  return parseFloat(param) > 0;
};

const positiveOrZero = (param) => {
  return parseFloat(param) >= 0;
};

const getFieldValidationResult = (formValidationResult, fieldKey) => {
  return formValidationResult.indexOf(fieldKey) > -1 ? "field-invalid" : "";
};

export {
  validateForm,
  getFieldValidationResult,
  notEmpty,
  notNull,
  positive,
  positiveOrZero,
};
