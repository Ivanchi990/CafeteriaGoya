package aad.cafeteriagoya.sqlite

import aad.cafeteriagoya.entidades.Producto
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class MiBDOpenHelper(contex: Context, factory: SQLiteDatabase.CursorFactory?) :
    SQLiteOpenHelper(contex, DATABASE_NAME, factory, DATABASE_VERSION) {

    val TAG = "SQLite"

    companion object {
        val DATABASE_VERSION = 1
        val DATABASE_NAME = "productos.db"
        val T_PRODUCTOS = "productos"
        val PRODUCTO_INSERCION_ID = "id_insercion_producto"
        val PRODUCTO_ID = "id_producto"
        val PRODUCTO_NOMBRE = "nombre"
        val PRODUCTO_PRECIO = "precio"
        val PRODUCTO_CATEGORIA = "categoria"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        var crearTablaPorductos = "CREATE TABLE $T_PRODUCTOS " +
                "($PRODUCTO_INSERCION_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "$PRODUCTO_ID INTEGER, " +
                "$PRODUCTO_NOMBRE TEXT," +
                "$PRODUCTO_PRECIO DOUBLE," +
                "$PRODUCTO_CATEGORIA TEXT)"
        var insercion_producto_prueba =
            "INSERT INTO $T_PRODUCTOS ($PRODUCTO_ID,$PRODUCTO_NOMBRE,$PRODUCTO_PRECIO,$PRODUCTO_CATEGORIA) " +
                    "VALUES (0,'Prueba',0.5,'pincho');"
        db!!.execSQL(crearTablaPorductos)
        db!!.execSQL(insercion_producto_prueba)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        Log.e("$TAG (onUpgrade)", "Pendiente de realizar")
    }


    fun andirProducto(p: Producto)
    {
        val db = this.writableDatabase
            val data = ContentValues()
            data.put(PRODUCTO_ID, p.id)
            data.put(PRODUCTO_NOMBRE, p.nombre)
            data.put(PRODUCTO_PRECIO, p.precio)
            data.put(PRODUCTO_CATEGORIA, p.categoria)

            db.insert(T_PRODUCTOS, null, data)
            db.close()
    }

    fun obtenerCarrito(): Cursor
    {
        val db = this.readableDatabase
        var cursor = db.rawQuery("SELECT * FROM ${MiBDOpenHelper.T_PRODUCTOS}", null)

        return cursor
    }


    fun eliminarProducto(pos: Int)
    {
        val db = this.writableDatabase

        val n = db.rawQuery("DELETE FROM ${MiBDOpenHelper.T_PRODUCTOS} WHERE ${MiBDOpenHelper.PRODUCTO_ID} = $pos", null)
    }
}