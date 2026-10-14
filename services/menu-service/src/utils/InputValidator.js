export class InputValidator {
    static hasEmptyFields(obj) {
        for (let key in obj) {
            if (obj[key] === "" || obj[key] === null || obj[key] === undefined || obj[key].trim?.() === "") {
                return true;
            }
        }
        return false;
    }

    static hasNumber(str) {
        return /\d/.test(str);
    }
    static isEmpty(value) {
        return value === "" || value === null || value === undefined || value.trim?.() === ""
    }

    static isPositiveNumber(value) {
        return typeof value === "number" && Number.isFinite(value) && value > 0;
    }
}