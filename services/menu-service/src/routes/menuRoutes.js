import { MenuController } from "../controllers/MenuController.js";

export default async function menuRoutes(fastify) {
    fastify.post('/menu', MenuController.createDish);
    fastify.delete('/menu', MenuController.deleteDish);
}