import dotenv from 'dotenv'

dotenv.config()

export default async function menuRoutes(fastify) {
    fastify.get('/menu', async (request, reply) => {
        try {
            const response = await fetch(process.env.URL_MENU)
            const data = await response.json()
            return reply.send(data)
        } catch (error) {
            return reply.status(500).send({
                message: 'Error accessing service'
            })
        }
    })
}
