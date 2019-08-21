import javax.persistence.criteria.CriteriaBuilder
import javax.persistence.criteria.CriteriaQuery
import javax.persistence.criteria.Root
import org.springframework.data.jpa.domain.Specifications

entityService = lepContext.services.xmEntity

def userProfiles = entityService.findAll(Specifications.where({ Root root, CriteriaQuery query, CriteriaBuilder cb ->
    return cb.and(cb.equal(root.get('typeKey'), 'USER'))
}))

def users = entityService.findAll(Specifications.where({ Root root, CriteriaQuery query, CriteriaBuilder cb ->
    return cb.equal(root.get('typeKey'), 'APPLICATION')
}))

for (def user: users) {
    for (def link: user.getTargets()) {
        userProfiles = userProfiles.stream()
                .filter { profile -> profile.id != link.target.id }
                .collect()
    }
}

Map<String, Object> map = ["data": userProfiles]
return map
