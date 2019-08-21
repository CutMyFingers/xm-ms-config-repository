import javax.persistence.criteria.CriteriaBuilder
import javax.persistence.criteria.CriteriaQuery
import javax.persistence.criteria.Root
import org.springframework.data.jpa.domain.Specifications
import org.slf4j.LoggerFactory

entityService = lepContext.services.xmEntity

def log = LoggerFactory.getLogger(getClass())
log.error('blah')

def users = entityService.findAll(Specifications.where({ Root root, CriteriaQuery query, CriteriaBuilder cb ->
    return cb.equal(root.get('typeKey'), 'USER')
}))

Map<String, String> map = ["data": "hey"]
return map
