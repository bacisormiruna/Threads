package DataAccess;

import Connection.ConnectionFactory;
import Model.Client;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static Connection.ConnectionFactory.getConnection;

/**
 * Clasa abstractă care definește operațiile de bază pentru accesul la date.
 * @param <T> Tipul obiectelor gestionate de clasă.
 */
public abstract class AbstractDAO<T> {
    protected static final Logger LOGGER = Logger.getLogger(AbstractDAO.class.getName());
    private final Class<T> tip;

   // @SuppressWarnings("unchecked")
    /**
     * Construiește un obiect AbstractDAO și determină tipul de obiect gestionat.
     */
    @SuppressWarnings("unchecked")
    public AbstractDAO() {
        this.tip = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    /**
     * Construiește o interogare SELECT bazată pe numele câmpului specificat.
     * @param field Numele câmpului după care se realizează căutarea.
     * @return Interogarea SELECT generată.
     */
    private String createSelectQuery(String field) {//creez query-ul de selectare dintr-un tabel a coloanelor
        StringBuilder interogare = new StringBuilder();
        interogare.append("SELECT * FROM ");
        interogare.append(tip.getSimpleName());
        interogare.append(" WHERE ").append(field).append(" =?");
        return interogare.toString();
    }

    /**
     * Găsește un obiect după ID.
     * @param id ID-ul obiectului căutat.
     * @return Obiectul cu ID-ul specificat sau null dacă nu este găsit.
     */
    public T findById(int id) {//gasirea unui obiect dupa id
        Connection connection = null;//variabila de tip conexiune
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectQuery("id");
        try {
            connection = getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();//rezultatul se stocheaza in resultSet

            List<T> result = createObjects(resultSet);
            if (result.isEmpty()) {//daca mi s-a generat un raspuns nenul
                return null;
            }
            return result.get(0);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, tip.getName() + "DAO:findById " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }

    /**
     * Returnează toate obiectele din tabel.
     * @return Lista obiectelor din tabel.
     */
    public List<T> findAll() {//imi returneaza lista cu toate datele dintr-un tabel dupa executia blocului de select dintr-un tabe;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = "SELECT * FROM " + tip.getSimpleName();
        try {
            connection = getConnection();
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
            return createObjects(resultSet);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, tip.getName() + "DAO:findAll " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }

    /**
     * Creează obiectele corespunzătoare din rezultatul unei interogări SELECT.
     * @param resultSet Rezultatul interogării SELECT.
     * @return Lista obiectelor create din rezultat.
     */
    private List<T> createObjects(ResultSet resultSet) {
        List<T> list = new ArrayList<>();
        Constructor<T> ctor = null;
        Constructor<?>[] ctors = tip.getDeclaredConstructors();
        for (Constructor<?> constructor : ctors) {
            if (constructor.getGenericParameterTypes().length == 0) {
                ctor = (Constructor<T>) constructor;
                break;
            }
        }
        if (ctor == null) {
            throw new RuntimeException("No no-arg constructor found for " + tip.getName());
        }
        try {
            while (resultSet.next()) {
                ctor.setAccessible(true);
                T instance = ctor.newInstance();
                for (Field field : tip.getDeclaredFields()) {
                    String fieldName = field.getName();
                    Object value = resultSet.getObject(fieldName);
                    PropertyDescriptor propertyDescriptor = new PropertyDescriptor(fieldName, tip);
                    Method method = propertyDescriptor.getWriteMethod();
                    method.invoke(instance, value);
                }
                list.add(instance);
            }
        } catch (InstantiationException | IllegalAccessException | IntrospectionException | InvocationTargetException |
                 SQLException e) {
            LOGGER.log(Level.WARNING, "Error creating instance for " + tip.getName(), e);
        }
        return list;
    }

    /**
     * Găsește un obiect după numele produsului.
     * @param name Numele produsului căutat.
     * @return Obiectul cu numele specificat sau null dacă nu este găsit.
     */
    public T findByName(String name) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectQuery("nameProduct");
        try {
            connection = getConnection();
            statement = connection.prepareStatement(query);
            statement.setString(1, name);
            resultSet = statement.executeQuery();

            List<T> result = createObjects(resultSet);
            if (result.isEmpty()) {
                return null;
            }
            return result.get(0);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, tip.getName() + "DAO:findByName " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }

    /**
     * Găsește un obiect după numele clientului.
     * @param name Numele clientului căutat.
     * @return Obiectul cu numele specificat sau null dacă nu este găsit.
     */
    public T findByNameC(String name) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectQuery("name");
        try {
            connection = getConnection();
            statement = connection.prepareStatement(query);
            statement.setString(1, name);
            resultSet = statement.executeQuery();

            List<T> result = createObjects(resultSet);
            if (result.isEmpty()) {
                return null;
            }
            return result.get(0);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, tip.getName() + "DAO:findByName " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }

    /**
     * Găsește un obiect după preț.
     * @param price Prețul obiectului căutat.
     * @return Obiectul cu prețul specificat sau null dacă nu este găsit.
     */
    public T findByPrice(double price) {//gasirea dupa pret cel mai probabil a unui produs
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectQuery("price");
        try {
            connection = getConnection();
            statement = connection.prepareStatement(query);
            statement.setDouble(1, price);
            resultSet = statement.executeQuery();

            List<T> result = createObjects(resultSet);
            if (result.isEmpty()) {
                return null;
            }
            return result.get(0);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, tip.getName() + "DAO:findByPrice " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }


    /**
     * Inserează un obiect în baza de date.
     * @param t Obiectul de inserat.
     */
    public void insert(T t) {//inserarea unui tip in tabelul specificat
        //Se începe construirea unei interogări INSERT INTO pentru a adăuga date într-un tabel al cărui nume este
        // determinat din tipul obiectului T.
        StringBuilder queryBuilder = new StringBuilder("INSERT INTO ");
        queryBuilder.append(tip.getSimpleName()).append(" (");

       // Se inițializează o parte a query-ului pentru secțiunea VALUES.
        StringBuilder valuesBuilder = new StringBuilder("VALUES (");
        Connection connection = null;
        PreparedStatement statement = null;

        //REFLEXIE!
        //Folosind reflecția, se obțin toate câmpurile declarate în clasa T. Pentru fiecare câmp:->se seteaza campul ca fiind accesibil
        //Se adaugă numele câmpului în lista de coloane a interogării.
        //Se adaugă un semn de întrebare ? în lista de valori, care va fi folosit ulterior ca parametru pentru PreparedStatement.
        //PreparedStatement este o interfață din pachetul java.sql folosită pentru a executa interogări SQL precompilate și parametrizate în Java. Iată câteva aspecte cheie despre PreparedStatement:
        try {
            for (Field field : tip.getDeclaredFields()) {
                field.setAccessible(true);
                queryBuilder.append(field.getName()).append(",");
                valuesBuilder.append("?,");
            }
            queryBuilder.setLength(queryBuilder.length() - 1);
            valuesBuilder.setLength(valuesBuilder.length() - 1);
            queryBuilder.append(") ").append(valuesBuilder).append(")");

            connection = getConnection();
            statement = connection.prepareStatement(queryBuilder.toString());

            //Pentru fiecare câmp al obiectului T, se setează valoarea corespunzătoare în PreparedStatement. Indexul parametrilor începe de la 1.
            int index = 1;
            for (Field field : tip.getDeclaredFields()) {
                field.setAccessible(true);
                statement.setObject(index++, field.get(t));
            }
            //Interogarea este executată, inserând datele în tabel.
            statement.execute();
        } catch (IllegalAccessException | SQLException e) {
            LOGGER.log(Level.WARNING, tip.getName() + "DAO:insert " + e.getMessage());
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
    }

    /**
     * Actualizează un obiect în baza de date.
     * @param t Obiectul de actualizat.
     */
    public void update(T t) {
        StringBuilder queryBuilder = new StringBuilder("UPDATE ");
        queryBuilder.append(tip.getSimpleName()).append(" SET ");
        Connection connection = null;
        PreparedStatement statement = null;
        int id = 0;

        //REFLEXIE!
        //Folosind reflecția, se obțin toate câmpurile declarate în clasa T. Pentru fiecare câmp:
        //Se adaugă numele câmpului în lista de coloane a interogării.
        //Se adaugă un semn de întrebare ? în lista de valori, care va fi folosit ulterior ca parametru pentru PreparedStatement.
        try {
            for (Field field : tip.getDeclaredFields()) {
                field.setAccessible(true);
                Object value = field.get(t);
                if ("id".equals(field.getName())) {
                    id = (int) value;
                } else {
                    queryBuilder.append(field.getName()).append("=?,");
                }
            }
            queryBuilder.setLength(queryBuilder.length() - 1); // Remove last comma
            queryBuilder.append(" WHERE id=?");

            connection = getConnection();
            statement = connection.prepareStatement(queryBuilder.toString());

            int index = 1;
            for (Field field : tip.getDeclaredFields()) {
                field.setAccessible(true);
                if (!"id".equals(field.getName())) {
                    statement.setObject(index++, field.get(t));
                }
            }
            statement.setInt(index, id);
            statement.execute();
        } catch (IllegalAccessException | SQLException e) {
            LOGGER.log(Level.WARNING, tip.getName() + "DAO:update " + e.getMessage());
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
    }

    /**
     * Șterge un obiect din baza de date.
     * @param t Obiectul de șters.
     */
    public void delete(T t) {
        String query = "DELETE FROM " + tip.getSimpleName() + " WHERE id=?";
        Connection connection = null;
        PreparedStatement statement = null;

        //REFLEXIE!
        //Folosind reflecția, se obțin toate câmpurile declarate în clasa T. Pentru fiecare câmp:
        //Se adaugă numele câmpului în lista de coloane a interogării.
        //Se adaugă un semn de întrebare ? în lista de valori, care va fi folosit ulterior ca parametru pentru PreparedStatement.
        try {
            connection = getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, getId(t));
            statement.execute();
        } catch (IllegalAccessException | SQLException e) {
            LOGGER.log(Level.WARNING, tip.getName() + "DAO:delete " + e.getMessage());
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
    }

    /**
     * Creează obiectele corespunzătoare din rezultatul unei interogări SELECT.
     * @param t Rezultatul interogării SELECT.
     * @return Lista obiectelor create din rezultat.
     */
    private int getId(T t) throws IllegalAccessException {
        for (Field field : tip.getDeclaredFields()) {
            field.setAccessible(true);
            if ("id".equals(field.getName())) {
                return (int) field.get(t);
            }
        }
        throw new IllegalArgumentException("No id field found in " + tip.getName());
    }

    public static <T> void setField(List<T> lista, JTable tabel) {
        if (lista == null || lista.isEmpty()) {
            throw new IllegalArgumentException("Lista nu trebuie să fie null sau goală");
        }
        // Obținerea clasei tipului T
        Class<?> class1 = lista.get(0).getClass();
        Field[] fields = class1.getDeclaredFields();

        // Extrage numele câmpurilor pentru antetul tabelului
        String[] columnNames = new String[fields.length];
        for (int i = 0; i < fields.length; i++) {
            fields[i].setAccessible(true);
            columnNames[i] = fields[i].getName();
        }

        // Crearea modelului de tabel
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        // Popularea datelor în tabel
        for (T obj : lista) {
            Object[] rowData = new Object[fields.length];
            for (int i = 0; i < fields.length; i++) {
                try {
                    rowData[i] = fields[i].get(obj);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                    rowData[i] = "NULL";
                }
            }
            model.addRow(rowData);
        }
        // Setarea modelului de tabel în JTable
        tabel.setModel(model);
    }
}

