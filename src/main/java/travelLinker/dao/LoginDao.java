package travelLinker.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import travelLinker.entity.AccountBean;
import travelLinker.utils.PasswordUtils;
import travelLinker.viewModel.AccountViewModel;

@Stateless
public class LoginDao {

    @PersistenceContext
    private EntityManager entityManager;

    public boolean validate(AccountViewModel loginViewModel) {
        System.out.println(loginViewModel.getEmail() + loginViewModel.getPassword());

        try {
            String queryString = "SELECT a FROM AccountBean a WHERE a.email = :email";
            System.out.println(queryString);
            TypedQuery<AccountBean> query = entityManager.createQuery(queryString, AccountBean.class);
            query.setParameter("email", loginViewModel.getEmail());

            AccountBean accountBean = query.getSingleResult();

            // Vérifier si le mot de passe fourni par l'utilisateur correspond au mot de passe haché dans la base de données
            if (PasswordUtils.checkPassword(loginViewModel.getPassword(), accountBean.getPassword())) {
                return true; // Le mot de passe est correct, l'utilisateur est authentifié
            } else {
                return false; // Le mot de passe est incorrect
            }
        } catch (NoResultException ex) {
            System.out.println("Login error -->" + ex.getMessage());
        }
        return false; // L'utilisateur n'a pas été trouvé dans la base de données
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

}




