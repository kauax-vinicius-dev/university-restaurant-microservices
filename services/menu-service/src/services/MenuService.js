import { InputValidator } from "../utils/InputValidator.js";
import { MenuRepository } from "../repositories/MenuRepository.js";

export class MenuService {
    static async registerDish(obj) {

        if (InputValidator.hasEmptyFields(obj)) {
            throw new Error("Invalid dish data.");
        }
        if (!InputValidator.isString(obj.name) || !InputValidator.isString(obj.description)) {
            throw new Error("Invalid dish name or description.");
        }
        if (!InputValidator.isPositiveNumber(obj.price)) {
            throw new Error("Invalid dish price.");
        }
        if (!InputValidator.isBoolean(obj.available)) {
            throw new Error("Invalid dish availability.");
        }

        await this.createDish(obj);
    }

    static async createDish(obj) {
        await MenuRepository.createDish(obj);
    }

}