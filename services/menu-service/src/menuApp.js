import Fastify from "fastify";
import menuRoutes from "./routes/menuRoutes.js";

const fastify = Fastify({
    logger: true
})

fastify.register(menuRoutes);

fastify.listen({ port: 3020 }, function (err, address) {
    if (err) {
        fastify.log.error(err)
        process.exit(1)
    }
    console.log(address)
})
