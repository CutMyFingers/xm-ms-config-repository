import javax.persistence.criteria.CriteriaBuilder
import javax.persistence.criteria.CriteriaQuery
import javax.persistence.criteria.Root
import org.springframework.data.jpa.domain.Specifications

entityService = lepContext.services.xmEntity

def users = entityService.findAll(Specifications.where({ Root root, CriteriaQuery query, CriteriaBuilder cb ->
    return cb.equal(root.get('typeKey'), 'USER')
}))

Map<String, Object> map = ["data": users.first()]
return map
