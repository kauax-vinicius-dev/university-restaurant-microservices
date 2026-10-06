import dotenv from 'dotenv'

dotenv.config()

export default async function menuRoutes(fastify) {
    fastify.get('/menu', async (request, reply) => {
        try {
            const response = await fetch(process.env.URL_MENU)
            if (!response.ok) {
                return reply.status(response.status).send({
                    message: 'Menu Service returned an error'
                })
            }
            const data = await response.json()
            return reply.send(data)
        } catch (error) {
            return reply.status(500).send({
                message: 'Error accessing service'
            })
        }
    })
}
