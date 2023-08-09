package travelLinker.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import travelLinker.entity.Account;
import travelLinker.entity.RoleUser;
import travelLinker.utils.PasswordUtils;
import travelLinker.viewModel.AccountViewModel;

@Stateless
public class LoginDao {

    @PersistenceContext
    private EntityManager entityManager;

    public Account findAccountByEmail(String email) {
        try {
            TypedQuery<Account> query = entityManager.createQuery(
                "SELECT a FROM Account a WHERE a.email = :email", Account.class)
                .setParameter("email", email);

            return query.getSingleResult();
        } catch (NoResultException ex) {
            System.out.println("Utilisateur non trouvé dans la base de données");
            return null; // Ou lancez une exception appropriée
        }
    }
//------------------------------------------------------
    public Long getAccountIdByEmail(String email) {
        try {
            TypedQuery<Long> query = entityManager.createQuery("SELECT a.id FROM AccountBean a WHERE a.email = :email", Long.class);
            query.setParameter("email", email);
            return query.getSingleResult();
            
        } catch (NoResultException e) {
            // Si aucun compte ne correspond à l'adresse e-mail, retourner null ou une valeur par défaut appropriée.
            return null;
            
        } catch (NonUniqueResultException e) {
            // Si plusieurs comptes correspondent à l'adresse e-mail (ce qui ne devrait pas arriver), gérer l'exception ici
            System.out.println("Plusieurs comptes trouvés pour l'adresse e-mail : " + email);
            return null;
        } catch (Exception e) {
            // Gére les exception lors de l'exécution de la requête
            return null;
        }
    }
//------------------------------------------------------------

    public boolean validate(AccountViewModel accountVM) {
        Account accountBean = findAccountByEmail(accountVM.getEmail());

        if (accountBean != null) {
            return PasswordUtils.checkPassword(accountVM.getPassword(), accountBean.getPassword());
        }

        return false;
    }

    public Account findAccountByEmailAndPassword(AccountViewModel accountVM, String email, String password) {
        Account accountBean = findAccountByEmail(accountVM.getEmail());

        if (accountBean != null && PasswordUtils.checkPassword(accountVM.getPassword(), accountBean.getPassword())) {
            return accountBean;
        } else {
            System.out.println("Mot de passe incorrect");
            return null; // Ou lancez une exception appropriée
        }
    
    }

    }





