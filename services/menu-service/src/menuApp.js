import Fastify from "fastify";

const fastify = Fastify({
    logger: true
})

fastify.listen({ port: 3020 }, function (err, address) {
    if (err) {
        fastify.log.error(err)
        process.exit(1)
    }
    console.log(address)
})
