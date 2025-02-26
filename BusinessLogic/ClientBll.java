package BusinessLogic;

import java.util.List;
import java.util.NoSuchElementException;
import DataAccess.*;
import Model.*;
/**
 * Busiiness Logic ne ajuta la managementul clientilor
 * Aceasta clasa ofera metode de interactiune cu datele clientilor, cum ar fi inserarea, stergerea, actualizarea , sau gasirea acestora.
 */
public class ClientBll {
    private ClientDAO client;

    /**
     * Constructorul pentru  ClientBll
     * Initializarea instantei ClientDAO.
     */
    public ClientBll() {
        this.client = new ClientDAO();
    }

    /**
     * Returneaza toti clientii din baza de date.
     * @return Lista cu toti clientii.
     */
    public List<Client> selectall() {
        return client.findAll();
    }

    /**
     * Gasirea clientului dupa ID
     * @param id ID-ul clientului care se doreste a fi gasit.
     * @return Clientul cu ID-ul specificat.
     * @throws NoSuchElementException Daca nu a fost gasit niciun client cu ID-ul specificat.
     */
    public Client findClientById(int id) {
        Client client1 = client.findById(id);
        if (client1 == null) {
            throw new NoSuchElementException("The client with id =" + id + " was not found!");
        }
        return client1;
    }

    /**
     * Gasirea unui client dupa nume.
     * @param name Numele clientului care se doreste a fi gasit.
     * @return Clientul cu numele specificat.
     * @throws NoSuchElementException Daca nu s-a gasit niciun client cu numele specificat.
     */
    public Client findClientByName(String name) {
        Client client1 = client.findByNameC(name);
        if (client1 == null) {
            throw new NoSuchElementException("The client with name =" + name + " was not found!");
        }
        return client1;
    }

    /**
     * Inserarea unui client nou in baza de date
     * @param client1 Clientul care se doreste a fi inserat.
     */
    public void insert(Client client1) {
        client.insert(client1);
    }

    /**
     * Actualizarea unui client existent din baza de date.
     * @param client1 Clientul care se doreste a fi actualizat.
     */
    public void update(Client client1) {
        client.update(client1);
    }

    /**
     * Sterge un client din baza de date.
     * @param client1 Clientul care se doreste a fi sters.
     */
    public void delete(Client client1) {
        client.delete(client1);
    }
}