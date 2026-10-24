import pool from "../config/dbConfig.js";

export class MenuRepository {
    static async createDish(dishData) {
        const sql = `
            INSERT INTO dish (name, description, price, available)
            VALUES (?, ?, ?, ?)
        `;

        const values = [
            dishData.name,
            dishData.description,
            dishData.price,
            dishData.available
        ];

        const [result] = await pool.execute(sql, values);

        return {
            id: result.insertId,
            ...dishData
        };
    }
}