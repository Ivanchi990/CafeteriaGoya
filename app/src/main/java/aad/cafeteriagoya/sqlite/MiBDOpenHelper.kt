package aad.cafeteriagoya.sqlite

import aad.cafeteriagoya.entidades.Producto
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class MiBDOpenHelper(contex: Context, factory: SQLiteDatabase.CursorFactory?) :
    SQLiteOpenHelper(contex, DATABASE_NAME, factory, DATABASE_VERSION)
{

    val TAG = "SQLite"

    companion object {
        val DATABASE_VERSION = 1
        val DATABASE_NAME = "productos.db"
        val T_PRODUCTOS = "productos"
        val PRODUCTO_INSERCION_ID = "id_insercion_producto"
        val PRODUCTOS = "productos"
    }

    override fun onCreate(db: SQLiteDatabase?)
    {
        var crearTablaPorductos = "CREATE TABLE $T_PRODUCTOS ($PRODUCTO_INSERCION_ID " +
                "INTEGER PRIMARY KEY AUTOINCREMENT, $PRODUCTOS TEXT)"
        var insercion_producto_prueba = "INSERT INTO $T_PRODUCTOS($PRODUCTOS) VALUES('0-0-0-0');"

        db!!.execSQL(crearTablaPorductos)
        db!!.execSQL(insercion_producto_prueba)
    }


    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int)
    {
        Log.e("$TAG (onUpgrade)", "Pendiente de realizar")
    }


    fun andirProducto(productos: String)
    {
        val db = this.writableDatabase

        var insercion = "INSERT INTO $T_PRODUCTOS(${MiBDOpenHelper.PRODUCTOS}) VALUES('$productos');"

        db!!.execSQL(insercion)

        db.close()
    }


    fun obtenerCarrito(): Cursor
    {
        val db= this.readableDatabase
        var cursor = db.rawQuery("SELECT * FROM ${MiBDOpenHelper.T_PRODUCTOS}", null)

        return cursor
    }


    fun obtenerPedido(pos: Int): Cursor
    {
        val db = this.readableDatabase
        var cursor = db.rawQuery("SELECT * FROM ${MiBDOpenHelper.T_PRODUCTOS} WHERE $PRODUCTO_INSERCION_ID = $pos", null)

        return cursor
    }
}