import Fastify from 'fastify'
import menuRoutes from './routes/menuRoutes.js'
import cors from '@fastify/cors'

const fastify = Fastify({
    logger: true
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

