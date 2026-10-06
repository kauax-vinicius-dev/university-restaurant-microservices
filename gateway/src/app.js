import Fastify from 'fastify'
import menuRoutes from './routes/menuRoutes.js'
import cors from '@fastify/cors'
import rateLimit from '@fastify/rate-limit'

const fastify = Fastify({
    logger: true
})

await fastify.register(rateLimit, {
    global: true,
    max: 100,
    timeWindow: '1 minute',
    skipOnError: false
})

fastify.register(menuRoutes)

fastify.register(cors, {
    origin: '*',
    methods: ['GET', 'POST', 'PUT', 'DELETE'],
    credentials: true
})

fastify.listen({ port: 3000 }, function (err, address) {
    if (err) {
        fastify.log.error(err)
        process.exit(1)
    }
    console.log(address)
})

