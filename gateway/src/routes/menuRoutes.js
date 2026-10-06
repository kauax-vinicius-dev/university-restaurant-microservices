import dotenv from 'dotenv'
import { handleServiceError } from '../utils/errorHandler.js'

dotenv.config()

export default async function menuRoutes(fastify) {
    fastify.get('/menu', async (request, reply) => {
        try {
            const response = await fetch(process.env.URL_MENU)
            if (!response.ok) {
                return reply.status(response.status).send({
                    message: handleServiceError(response.status)
                })
            }
            const data = await response.json()
            return reply.send(data)
        } catch (error) {
            return reply.status(502).send({
                message: 'Unable to access Menu Service'
            })
        }
    })
}
