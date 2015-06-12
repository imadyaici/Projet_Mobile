package com.example.gottesdiener.projet;


import android.content.ContentValues;
import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;


public class DataBaseHandler extends SQLiteOpenHelper {
    static final String DB_NAME ="bibdb";
    static final int DB_VERSION = 1;
    static final String bookCreation = "create table book " +
        "(id integer primary key,categorie text, titre text,auteur text,annee text, resume text,image blob)";
    static final String countCreation = "create table count" +
            "(categorie text primary key, nombre integer)";
    static final String abstract1 ="Are you looking for a deeper understanding of the Java™ programming language so that you can write code that is clearer, more correct, more robust, and more reusable? Look no further! Effective Java™, Second Edition, brings together seventy-eight indispensable programmer’s rules of thumb: working, best-practice solutions for the programming challenges you encounter every day";
    static final String abstract2 ="Assuming no previous experience in the field of computer security, this must-have book walks you through the many essential aspects of this vast topic, from the newest advances in software and technology to the most recent information on Web applications security. This new edition includes sections on Windows NT, CORBA, and Java and discusses cross-site scripting and JavaScript hacking";
    private Context ctx;

    public DataBaseHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.ctx = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(bookCreation);
        db.execSQL(countCreation);
        // get cover image book
        Resources rs = ctx.getResources();
        initCount(db);

        /*******************************************************************************/
        Bitmap image = BitmapFactory.decodeResource(rs, R.drawable.effective_java);
        String resume = "Effective java is in 3rd number in the list of best books for learning java. This book will teach you how to effectively use and optimize java in real world.";
        Livre livre = new Livre("Java","Effective Java","Joshua Bloch",resume,getImageByte(image));
        addBook(livre,db);

        image = BitmapFactory.decodeResource(rs, R.drawable.head_first_java);
        resume = "This is the first door to start moving your feet towards mastery in java. The best thing about this book is image visualization.";
        livre = new Livre("Java","Head First Java","Kathy siera and bert bates",resume,getImageByte(image));
        addBook(livre,db);

        image = BitmapFactory.decodeResource(rs, R.drawable.certified_programmer_for_java_6);
        resume = "This is the must read java book for beginners. This book has covered all the small tidbits about the common terms such as constructors , class , operators ,exceptions etc.";
        livre = new Livre("Java","Certified Programmer for Java 6","Kathy siera and bert bates",resume,getImageByte(image));
        addBook(livre,db);

        image = BitmapFactory.decodeResource(rs, R.drawable.java_concurrency_in_practice);
        resume = "If you are pursuing career as a java developer, then this is the book you will surely going to enjoy.";
        livre = new Livre("Java","Java Concurrency in Practice","Tim Peierle",resume,getImageByte(image));
        addBook(livre,db);

        image = BitmapFactory.decodeResource(rs, R.drawable.thinking_in_java);
        resume = "Thinking in java is a well written book by Bruce Eckel. The topics from which you can learn a lot is Containers in depth, Annotations and Graphical User Interface.";
        livre = new Livre("Java","Thinking in java","Tim Peierle",resume,getImageByte(image));
        addBook(livre,db);

        /*******************************************************************************/

        image = BitmapFactory.decodeResource(rs, R.drawable.introducingmicrosoftsqlserver2014);
        resume = "Looking for a comprehensive, technical overview of the capabilities and features of SQL Server 2014? If so, you'll want to download this free ebook from Microsoft Press.";
        livre = new Livre("SQL","Introducing Microsoft SQL Server 2014","Ross Mistry",resume,getImageByte(image));
        addBook(livre,db);

        image = BitmapFactory.decodeResource(rs, R.drawable.microsoftsqlserver2012internals);
        resume = "Explore the architecture and core engine of SQL Server 2012.";
        livre = new Livre("SQL","Microsoft SQL Server 2012 Internals","Kalen Delaney",resume,getImageByte(image));
        addBook(livre,db);

        image = BitmapFactory.decodeResource(rs, R.drawable.tsqlfundamentals);
        resume = "Get a great introduction to T-SQL (also known as Transact-SQL).";
        livre = new Livre("SQL","Microsoft SQL Server 2012 T-SQL Fundamentals","Itzik Ben-Gan",resume,getImageByte(image));
        addBook(livre,db);

        image = BitmapFactory.decodeResource(rs, R.drawable.sqlserver2012queryperformancetuning);
        resume = "This book teaches you about the causes of poor performance, how to identify them, and how to fix them.";
        livre = new Livre("SQL","SQL Server 2012 Query Performance Tuning","Grant Fritchey",resume,getImageByte(image));
        addBook(livre,db);

        image = BitmapFactory.decodeResource(rs, R.drawable.sqlserver2012internalstroubleshooting);
        resume = "This book provides in-depth coverage of best practices for troubleshooting performance problems and shows DBAs how to ensure reliable performance.";
        livre = new Livre("SQL","Professional SQL Server 2012 Internals and Troubleshooting","Rob Farley",resume,getImageByte(image));
        addBook(livre,db);
        /*******************************************************************************/

        image = BitmapFactory.decodeResource(rs, R.drawable.phpforabsolutebeginners2);
        resume = "Just as the name implies, PHP for Absolute Beginners is written for people who starts at the very beginning of web programming, even a complete user.";
        livre = new Livre("PHP","Best PHP Books","Jason Lengstorf",resume,getImageByte(image));
        addBook(livre,db);

        image = BitmapFactory.decodeResource(rs, R.drawable.programmingphp2);
        resume = "It is a great book to help people building websites. The latest edition involves everything needed to create effective web applications with the newest features in PHP 5.x.";
        livre = new Livre("PHP","Programming PHP","Oreilly",resume,getImageByte(image));
        addBook(livre,db);

        image = BitmapFactory.decodeResource(rs, R.drawable.phpadvancedandobjorientedpro2);
        resume = "PHP Objects, Patterns and Practices will provide professional programmers with a platform to acquire more advanced programming knowledge beyond the PHP basics.";
        livre = new Livre("PHP","PHP Advanced and Object-Oriented Programming","Matt Zandstra",resume,getImageByte(image));
        addBook(livre,db);

        image = BitmapFactory.decodeResource(rs, R.drawable.phpcookbook2);
        resume = "PHP Cookbook: Solutions and Examples for PHP Programmers is a good option for building dynamic websites that work on any web browser.";
        livre = new Livre("PHP","PHP Cookbook","Oreilly",resume,getImageByte(image));
        addBook(livre,db);

        image = BitmapFactory.decodeResource(rs, R.drawable.phpmysqljsandcss2);
        resume = "If users have quiet understandings of HTML and want to create interactive, data-driven websites with powerful programming languages, Learning MySQL, JavaScript and CSS can do them a favor, even for users who do not have any programming experience.";
        livre = new Livre("PHP","Learning PHP, MySQL, JavaScript, and CSS","Oreilly",resume,getImageByte(image));
        addBook(livre,db);

        /*******************************************************************************/
        image = BitmapFactory.decodeResource(rs, R.drawable.js1);
        resume = "A quick (less that 200 pages), thorough introduction to what you need to go to get up and running with JavaScript.";
        livre = new Livre("Javascript","Eloquent JavaScript","Marijn Haverbecke",resume,getImageByte(image));
        addBook(livre,db);

        image = BitmapFactory.decodeResource(rs, R.drawable.js1);
        resume = "A deeper and somewhat more readable tutorial than the first book, but solely focused on JavaScript and Ajax.";
        livre = new Livre("Javascript","The Book of JavaScript, 2nd Edition","Dave Thau",resume,getImageByte(image));
        addBook(livre,db);

        image = BitmapFactory.decodeResource(rs, R.drawable.js2);
        resume = "This is the definitive treatment of the language. Weighing in at more than 1000 pages, it performs deep dives into virtually every aspect of the language and of client-side Web apps.";
        livre = new Livre("Javascript","JavaScript, The Definitive Guide","David Flanagan",resume,getImageByte(image));
        addBook(livre,db);

        image = BitmapFactory.decodeResource(rs, R.drawable.js2);
        resume = "This is the other book I recommend every JavaScript developer own.";
        livre = new Livre("Javascript","Effective JavaScript","David Herman",resume,getImageByte(image));
        addBook(livre,db);

        image = BitmapFactory.decodeResource(rs, R.drawable.js3);
        resume = "JavaScript performance used to be a really serious problem, but the advances in the various engines and virtual machines have greatly improved responsiveness of Web apps that rely on JavaScript";
        livre = new Livre("Javascript","Pro JavaScript Performance","Tom Barker",resume,getImageByte(image));
        addBook(livre,db);

        /*********************************************************************************/

        image = BitmapFactory.decodeResource(rs, R.drawable.head1sthtmlcssxhtml);
        resume = "This book will introduce a complete practice in web page markup and presentation.";
        livre = new Livre("Html","Head First HTML with CSS & XHTML","Oreilly",resume,getImageByte(image));
        addBook(livre,db);

        image = BitmapFactory.decodeResource(rs, R.drawable.htmlcss1);
        resume = "HTML and CSS: Visual QuickStart Guide features best practices of web design these days.";
        livre = new Livre("Html","HTML and CSS: Visual QuickStart Guide","Unknown",resume,getImageByte(image));
        addBook(livre,db);

        image = BitmapFactory.decodeResource(rs, R.drawable.learningwebdesign1);
        resume = "This book suits to both students and professionals of all backgrounds and skill levels.";
        livre = new Livre("Html","Learning Web Design","Oreilly",resume,getImageByte(image));
        addBook(livre,db);

        image = BitmapFactory.decodeResource(rs, R.drawable.htmlcssdesignbuild1);
        resume = "This book is for beginners without a technical background. It gives the simple and concise introduction to the concept of web programming and HTML and CSS.";
        livre = new Livre("Html","HTML and CSS: Design and Build Websites","John Duchett",resume,getImageByte(image));
        addBook(livre,db);

        image = BitmapFactory.decodeResource(rs, R.drawable.htmlxhtml1);
        resume = "This book gives elaborate instructions for you to master standards-based web development.";
        livre = new Livre("Html","HTML & XHTML: The Definitive Guide","Oreilly",resume,getImageByte(image));
        addBook(livre,db);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS book");
        onCreate(db);
    }

   public byte[] getImageByte(Bitmap image) {
       ByteArrayOutputStream stream = new ByteArrayOutputStream();
       image.compress(Bitmap.CompressFormat.PNG, 100, stream);
       byte[] imageByte = stream.toByteArray();
       return imageByte;
   }

    public void addBook (Livre book,SQLiteDatabase db) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("categorie", book.getCategorie());
        contentValues.put("titre",book.getTitre());
        contentValues.put("auteur",book.getAuteur());
        contentValues.put("annee",book.getAnneeEdition());
        contentValues.put("resume",book.getResume());
        contentValues.put("image",book.getImageView());
        db.insert("book", null, contentValues);
    }

    public void initCount(SQLiteDatabase db){
        ContentValues contentValues = new ContentValues();
        contentValues.put("categorie","Java");
        contentValues.put("nombre",1);
        db.insert("count",null,contentValues);
        contentValues.put("categorie","SQL");
        contentValues.put("nombre",1);
        db.insert("count",null,contentValues);
        contentValues.put("categorie","PHP");
        contentValues.put("nombre",1);
        db.insert("count", null, contentValues);
        contentValues.put("categorie","Javascript");
        contentValues.put("nombre",1);
        db.insert("count", null, contentValues);
        contentValues.put("categorie","Html");
        contentValues.put("nombre",1);
        db.insert("count",null,contentValues);
    }


    public Livre getBookByTitle(String title) {
        Livre book =null;
        String query ="select * from book where lower(titre)=?";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, new String[]{title.toLowerCase()});
        if (cursor.moveToFirst()) {
            book = new Livre();
            book.setId(cursor.getInt(0));
            book.setCategorie(cursor.getString(1));
            book.setTitre(cursor.getString(2));
            book.setAuteur(cursor.getString(3));
            book.setAnneeEdition(cursor.getString(4));
            book.setResume(cursor.getString(5));
            book.setImageView(cursor.getBlob(6));
        }
        db.close();
        return book;
    }

    public int getCount(String categorie) {
        int count = 0;
        String query ="select nombre from count where lower(categorie)=?";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, new String[]{categorie.toLowerCase()});
        if (cursor.moveToFirst()) {
            count = cursor.getInt(0);
        }
        db.close();
        return count;
    }

    public void updateCount(String categorie, int count){
        ContentValues newValues = new ContentValues();
        newValues.put("nombre", count);
        SQLiteDatabase db = this.getWritableDatabase();
        Log.d("count", categorie);
        int s = db.update("count", newValues, "lower(categorie) = ?", new String[]{categorie.toLowerCase()});

    }

    public ArrayList<Livre> getAllBooks() {
        ArrayList<Livre> livresList = new ArrayList<Livre>();
        SQLiteDatabase db = this.getReadableDatabase();
        String query= "select * from book ";

        Cursor cursor = db.rawQuery(query,null);
        if(cursor.moveToFirst()) {
            do {
                Livre book = new Livre();
                book.setId(cursor.getInt(0));
                book.setCategorie(cursor.getString(1));
                book.setTitre(cursor.getString(2));
                book.setAuteur(cursor.getString(3));
                book.setAnneeEdition(cursor.getString(4));
                book.setResume(cursor.getString(5));
                book.setImageView(cursor.getBlob(6));
                livresList.add(book);
            }while(cursor.moveToNext());
        }
        db.close();
        return livresList;
    }
}
