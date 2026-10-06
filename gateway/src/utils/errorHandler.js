export function handleServiceError(status) {

    const messageError = {
        400: 'Invalid request',
        401: 'Unauthorized',
        403: 'Forbidden',
        404: 'Resource not found',
        409: 'Conflict',
        429: 'Too many requests',
        500: 'Internal server error',
        502: 'Bad gateway',
        503: 'Service unavailable',
        504: 'Gateway timeout'
    }

    return messageError[status] || 'Service returned an error'
}