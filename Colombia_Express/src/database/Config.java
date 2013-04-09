package database;
public class Config {
	
	private String version = "0.1.7.sqlite";
	private String path;
	
	public String Path(){
		path = "file:///SDCard/Databases/BuzzCOApp/BuzzCO_"+version;
		return path;		
	}
	public String PathImg1(){
		path = "file:///SDCard/Databases/BuzzApp/Image/";
		return path;		
	}
	public String PathImg2(){
		path = "file:///SDCard/Databases/BuzzApp/Image/";
		return path;		
	}
	public String PathNoticia(){
		path = "file:///SDCard/PromoApp/Image/Noticia/";
		return path;		
	}
	public String PathAfiliado(){
		path = "file:///SDCard/PromoApp/Image/Afiliado/";
		return path;		
	}
	
	/*==============CREAR===============*/

	public String CreateUsuario(){
		String Createusuario = "CREATE TABLE IF NOT EXISTS USUARIO(id_User INTEGER UNIQUE, nombre TEXT, apellido TEXT, correo TEXT)";
		return Createusuario;
	}
	
	public String CreateCategoria(){
		String Createcategoria = "CREATE TABLE IF NOT EXISTS CATEGORIA(id_Cat TEXT, nombre_cat TEXT)";
		return Createcategoria;
	}
	
	public String CreateAfiliado(){
		String Createafiliado = "CREATE TABLE IF NOT EXISTS AFILIADO(id_Cat TEXT, id_Afi TEXT, nombre_Afi TEXT, logo_Afi TEXT, slogan_Afi TEXT, ubicacion_Afi TEXT)";
		return Createafiliado;
	}
	
	public String CreatePromocion(){
		String Createpromocion = "CREATE TABLE IF NOT EXISTS PROMOCION(id_Promo TEXT, id_Afi TEXT, nombre_Promo TEXT, logo_Promo TEXT, desc_Promo TEXT, fecha_Promo TEXT, numero_Promo TEXT)";
		return Createpromocion;
	}
	
	public String CreateContacto(){
		String Createcontacto = "CREATE TABLE IF NOT EXISTS CONTACTO(id_Afi TEXT, id_Contacto TEXT, nombre_Contacto TEXT, tipo_Contacto TEXT)";
		return Createcontacto;
	}
	

	public String CreateOferta(){
		String Createoferta = "CREATE TABLE IF NOT EXISTS OFERTA(idOferta TEXT, id_Afi TEXT, nombre_Oferta TEXT, imagen_Oferta TEXT, desc_Oferta TEXT, fecha_Oferta TEXT, precio_Oferta TEXT, precio_Regular TEXT)";
		return Createoferta;
	}	
	
	public String CreateNoticia(){
		String Createnoticia = "CREATE TABLE IF NOT EXISTS NOTICIA(idNoticia TEXT, id_Afi TEXT, titulo_Noticia TEXT, contenido_Noticia TEXT, fecha_Noticia TEXT, imagen_Noticia TEXT)";
		return Createnoticia;
	}
	/*============INSERTAR==============*/	
	
	public String InsertUsuario(){
		String Insertusuario = "INSERT INTO USUARIO(id_User,nombre,apellido,correo)VALUES(";
		return Insertusuario;
	}
	
	public String InsertCategoria(){
		String Insertcategoria = "INSERT INTO CATEGORIA(id_Cat,nombre_Cat)VALUES(";
		return Insertcategoria;
	}
	
	public String InsertAfiliado(){
		String Insertafiliado = "INSERT INTO AFILIADO(id_Cat,id_Afi,nombre_Afi,logo_Afi,slogan_Afi,ubicacion_Afi)VALUES(";
		return Insertafiliado;
	}
	
	public String InsertPromocion(){
		String Insertpromocion = "INSERT INTO PROMOCION(id_Promo,id_Afi,nombre_Promo,logo_Promo,desc_Promo,fecha_Promo,numero_Promo)VALUES(";
		return Insertpromocion;
	}
	
	public String InsertOferta(){
		String Insertoferta = "INSERT INTO OFERTA(idOferta,id_Afi,nombre_Oferta,imagen_Oferta,desc_Oferta,fecha_Oferta,precio_Oferta,precio_Regular)VALUES(";
		return Insertoferta;
	}
	
	public String InsertNoticia(){
		String Insertnoticia = "INSERT INTO NOTICIA(idNoticia,id_Afi,titulo_Noticia,contenido_Noticia,fecha_Noticia,imagen_Noticia)VALUES(";
		return Insertnoticia;
	}
	
	
	
	/*==========SELECCIONAR=============*/
	
	
	public String SelectUsuario(){
		String Selectusuario = "SELECT nombre FROM USUARIO WHERE id_User = 1";
		return Selectusuario;
	}
	
	public String SelectCategoria(){
		String TodoCategoria = "SELECT * FROM CATEGORIA";
		return TodoCategoria;
	}
	
	
	public String SelectAfiliado(){
		String Selectafiliado = "SELECT * FROM AFILIADO WHERE id_Cat = ";
		return Selectafiliado;
	}
	
	public String SelectPromocion(){
		String Selectpromocion = "SELECT * FROM PROMOCION WHERE id_Afi = ";
		return Selectpromocion;
	}
	public String SelectPromoDesc(){
		String Selectpromodesc = "SELECT id_Promo,id_Afi,nombre_Promo,logo_Promo,desc_Promo,fecha_Promo,numero_Promo FROM PROMOCION WHERE id_Promo = ";
		return Selectpromodesc;
	}
	public String SelectOferta(){
		String Selectoferta = "SELECT * FROM OFERTA WHERE id_Afi = ";
		return Selectoferta;
	}
	public String SelectOfertaDesc(){
	String Selectofertadesc = "SELECT idOferta,id_Afi,nombre_Oferta,imagen_Oferta,desc_Oferta,fecha_Oferta,precio_Oferta,precio_Regular FROM OFERTA WHERE idOferta = ";
	return Selectofertadesc;
	}
	public String SelectNoticia(){
		String Selectnoticia = "SELECT * FROM NOTICIA WHERE id_Afi = ";
		return Selectnoticia;
	}
	public String SelectNoticiaDesc(){
		String Selectnoticiadesc = "SELECT * FROM NOTICIA WHERE idNoticia = ";
		return Selectnoticiadesc;
	}
	
	
	
	
	
	
	
	
	
	/*============ELIMINAR==============*/

	
	public String DeleteTableAfiliado(){
		String DeleteTableafiliado = "DELETE FROM AFILIADO WHERE id_Cat = ";
		return DeleteTableafiliado;
	}
	public String DeleteTableCategoria(){
		String statement = "DELETE FROM CATEGORIA";
		return statement;
	}
	public String DeleteTablePromociones(){
		String DeleteTablepromociones = "DELETE FROM PROMOCION WHERE id_Afi = ";
		return DeleteTablepromociones;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
