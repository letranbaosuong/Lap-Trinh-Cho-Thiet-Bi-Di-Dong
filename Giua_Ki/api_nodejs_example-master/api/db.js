'use strict';
const mysql = require('mysql');

const db = mysql.createConnection({
  host: process.env.DB_HOST || "45.252.249.11",
  user: process.env.DB_USER || "hoangva1_letranbaosuong",
  password: process.env.DB_PASSWORD || "letranbaosuong",
  database: process.env.DB_NAME || "hoangva1_SuongDB"
});

module.exports = db
