import pool from "../config/dbConfig.js";

async function testConnection() {
    try {
        const [rows] = await pool.query('SELECT 1 AS resultado');
        console.log('MySQL connection established successfully!');
        console.log(rows);
    } catch (error) {
        console.error('Error connecting to MySQL:', error.message);
    } finally {
        await pool.end();
    }
}

testConnection();