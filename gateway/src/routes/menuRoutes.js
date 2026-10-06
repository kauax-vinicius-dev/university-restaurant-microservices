import dotenv from 'dotenv'
import proxy from '@fastify/http-proxy'

dotenv.config()

export default async function menuRoutes(fastify) {
    fastify.register(proxy, {
        upstream: process.env.URL_MENU
    })
}
