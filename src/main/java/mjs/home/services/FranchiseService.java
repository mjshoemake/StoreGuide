package mjs.home.services;

import mjs.common.core.BaseService;
import mjs.common.crypto.EncryptionManager;
import mjs.common.crypto.Encryptor;
import mjs.common.exceptions.LoginException;
import mjs.model.User;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class FranchiseService extends BaseService {

    public FranchiseService() {
        super("mjs.model.Franchise", "franchise", "name", "franchise_pk", "mjs.model.Franchise");
    }
}
