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





