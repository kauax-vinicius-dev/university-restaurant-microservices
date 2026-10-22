import { MenuService } from "../services/MenuService.js";

export class MenuController {
    static async createDish(request, reply) {
        try {
            const dishData = request.body;
            await MenuService.registerDish(dishData);
            return reply.status(201).send({
                message: 'Dish created successfully.'
            });

        } catch (error) {
            return reply.status(500).send({
                message: 'Unable to create dish.'
            });
        }
    }
}