package travelLinker.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
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

    public boolean validate(AccountViewModel accountVM) {
        System.out.println(accountVM.getEmail() + accountVM.getPassword());

        try {
            String queryString = "SELECT a FROM Account a WHERE a.email = :email";
            System.out.println(queryString);
            TypedQuery<Account> query = entityManager.createQuery(queryString, Account.class);
            query.setParameter("email", accountVM.getEmail());

            Account accountBean = query.getSingleResult();

            // Vérifier si le mot de passe fourni par l'utilisateur correspond au mot de passe haché dans la base de données
            if (PasswordUtils.checkPassword(accountVM.getPassword(), accountBean.getPassword())) {
                return true; // Le mot de passe est correct, l'utilisateur est authentifié
            } else {
                return false; // Le mot de passe est incorrect
            }
        } catch (NoResultException ex) {
            System.out.println("Login error -->" + ex.getMessage());
        }
        return false; // L'utilisateur n'a pas été trouvé dans la base de données
    }
    
    public Account findAccountByEmailAndPasswor(AccountViewModel accountVM, String email, String password) {


            String queryString = "SELECT a FROM Account a WHERE a.email = :email";
            System.out.println(queryString);
            TypedQuery<Account> query = entityManager.createQuery(queryString, Account.class);
            query.setParameter("email", accountVM.getEmail());
          	
            

            Account accountBean = query.getSingleResult();
            if (PasswordUtils.checkPassword(accountVM.getPassword(), accountBean.getPassword())) {
                Long connectedAccountId = accountBean.getId();
                RoleUser connectedAccountRole=accountBean.getRole();
                System.out.println(connectedAccountId);
                System.out.println(connectedAccountRole);
                return accountBean;
            }else {
            System.out.println(" L'utilisateur n'a pas été trouvé dans la base de données");
    }
			return accountBean;
			
        }

    }





