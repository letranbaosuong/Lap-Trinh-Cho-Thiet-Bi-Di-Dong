'use strict'

const util = require('util')
const mysql = require('mysql')
const db = require('./../db')
var o2x = require('object-to-xml');

const table = 'products'

module.exports = {
    get: (req, res) => {
        let sql = 'SELECT * FROM products'
        db.query(sql, (err, response) => {
            if (err) throw err
            if (response.length > 0) {
                // json
                res.json(response)

                // xml
                // res.set('Content-Type', 'text/xml');
                // res.send(o2x({
                //     '?xml version="1.0" encoding="utf-8"?': null,
                //     results: {
                //         result: 'ok',
                //         products: {
                //             product: response
                //         }
                //     }
                // }))
            } else {

                // xml
                // res.set('Content-Type', 'text/xml');
                // res.send(o2x({
                //     '?xml version="1.0" encoding="utf-8"?': null,
                //     results: {
                //         result: 'failed'
                //     }
                // }))
            }
        })
        setInterval(function () {
            db.query('SELECT 1')
        }, 5000)
    },
    detail: (req, res) => {
        let sql = 'SELECT * FROM products WHERE id = ?'
        db.query(sql, [req.params.productId], (err, response) => {
            if (err) throw err

            if (response.length > 0) {
                // json
                // res.json({ result: 'ok', response })
                res.json(response[0])

                // xml
                // res.set('Content-Type', 'text/xml');
                // res.send(o2x({
                //     '?xml version="1.0" encoding="utf-8"?': null,
                //     results: {
                //         result: 'ok',
                //         products: {
                //             product: response
                //         }
                //     }
                // }))
            } else {
                // json
                res.json({ result: 'failed' })

                // xml
                // res.set('Content-Type', 'text/xml');
                // res.send(o2x({
                //     '?xml version="1.0" encoding="utf-8"?': null,
                //     results: {
                //         result: 'failed'
                //     }
                // }))
            }
        })
    },
    update: (req, res) => {
        let data = req.body;
        let productId = req.params.productId;
        let sql = 'UPDATE products SET ? WHERE id = ?'
        db.query(sql, [data, productId], (err, response) => {
            if (err) throw err
            res.json({ message: 'Update success!' })
        })
    },
    store: (req, res) => {
        let data = req.body;
        let sql = 'INSERT INTO products SET ?'
        db.query(sql, [data], (err, response) => {
            if (err) throw err
            res.json({ message: 'Insert success!' })
        })
    },
    delete: (req, res) => {
        let sql = 'DELETE FROM products WHERE id = ?'
        db.query(sql, [req.params.productId], (err, response) => {
            if (err) throw err
            res.json({ message: 'Delete success!' })
        })
    }
}