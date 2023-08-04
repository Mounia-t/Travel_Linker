package travelLinker.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import travelLinker.entity.AccountBean;

import travelLinker.entity.CustomerBean;
import travelLinker.entity.PartnerBean;
import travelLinker.entity.RoleUser;
import travelLinker.entity.TravelPlannerBean;
import travelLinker.utils.PasswordUtils;

import travelLinker.viewModel.AccountViewModel;

@Stateless
public class AccountDao {

    @PersistenceContext(unitName = "travelLinker")
    private EntityManager entityManager;
    public Long insert(AccountViewModel accountVM) {
        AccountBean accountbean = new AccountBean();
        accountbean.setEmail(accountVM.getEmail());

        // Hacher le mot de passe avant de l'enregistrer dans la base de données
        String hashedPassword = PasswordUtils.hashPassword(accountVM.getPassword());
        accountbean.setPassword(hashedPassword);

        accountbean.setRole(accountVM.getRole());
        accountbean.setLastName(accountVM.getLastName());
        accountbean.setFirstName(accountVM.getFirstName());
        if (accountVM.getRole() == RoleUser.Customer) {
            insertCustomerInto(accountVM);
        } else if (accountVM.getRole() == RoleUser.TravelPlanner) {
            insertTravelPlanner(accountVM);
        } else if (accountVM.getRole() == RoleUser.Partner) {
            insertPartner(accountVM);
        }
     
        entityManager.persist(accountbean);
        entityManager.flush();
        return accountbean.getId();
    }

    public void insertCustomerInto(AccountViewModel accountVM) {
        if (accountVM.getRole()==RoleUser.Customer) {
            CustomerBean customer = new CustomerBean();
            customer.setEmail(accountVM.getEmail());
       
          
            customer.setLastName(accountVM.getLastName());
            customer.setFirstName(accountVM.getFirstName());
           
            entityManager.persist(customer);
        }
    }

    public void insertTravelPlanner(AccountViewModel accountVM) {
        if (accountVM.getRole()==RoleUser.TravelPlanner) { 
        TravelPlannerBean travelPlanner = new TravelPlannerBean();
        travelPlanner.setEmail(accountVM.getEmail());

      
        travelPlanner.setLastName(accountVM.getLastName());
        travelPlanner.setFirstName(accountVM.getFirstName());
       
        entityManager.persist(travelPlanner);
    }    }

    public void insertPartner(AccountViewModel accountVM) {
        if (accountVM.getRole()==RoleUser.Partner) {
        	PartnerBean partner = new PartnerBean();
        	partner.setFirstName(accountVM.getFirstName());
        	partner.setLastName(accountVM.getLastName());
        	partner.setEmail(accountVM.getEmail());
        	
        	partner.setPhoneNumber(accountVM.getPhoneNumber());
        	partner.setAddress(accountVM.getAddress());
        	partner.setSiret(accountVM.getSiret());
        	 entityManager.persist(partner);
        }
    }

    public AccountDao() {
    }

    /*public void persist(AccountViewModel accountVM) {
        this.entityManager.persist(accountVM);
        this.entityManager.flush();
    }*/
}