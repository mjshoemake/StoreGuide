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
public class StoreService extends BaseService {

    public StoreService() {
        super("mjs.model.Store", "store", "name", "store_pk", "mjs.model.Store");
    }
}
