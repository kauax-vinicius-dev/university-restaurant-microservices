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

    static async deleteDish(request, reply) {
        try {
            const id = request.body;
            await MenuService.deleteDish(id);
            return reply.status(200).send({
                message: "Dish deleted successfully."
            });

        } catch (error) {
            return reply.status(500).send({
                message: 'Error deleting dish.'
            });
        }
    }
}