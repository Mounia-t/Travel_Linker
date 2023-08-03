package travelLinker.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import travelLinker.entity.AccountBean;
import travelLinker.viewModel.AccountViewModel;

@Stateless
public class LoginDao {

    @PersistenceContext
    private EntityManager entityManager;

    public boolean validate(AccountViewModel loginViewModel) {
        try {
            String queryString = "SELECT a FROM AccountBean a WHERE a.email = :email AND a.password = :password";
            TypedQuery<AccountBean> query = entityManager.createQuery(queryString, AccountBean.class);
            query.setParameter("email", loginViewModel.getEmail());
            query.setParameter("password", loginViewModel.getPassword());

            AccountBean accountBean = query.getSingleResult();

            return accountBean != null;
        } catch (NoResultException ex) {
            System.out.println("Login error -->" + ex.getMessage());
        }
        return false;
    }
}