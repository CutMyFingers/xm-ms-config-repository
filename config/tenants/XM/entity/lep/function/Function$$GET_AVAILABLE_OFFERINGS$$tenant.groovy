import javax.persistence.criteria.CriteriaBuilder
import javax.persistence.criteria.CriteriaQuery
import javax.persistence.criteria.Root
import org.springframework.data.jpa.domain.Specifications

entityService = lepContext.services.xmEntity

def jobProfiles = entityService.findAll(Specifications.where({ Root root, CriteriaQuery query, CriteriaBuilder cb ->
    return cb.and(cb.equal(root.get('typeKey'), 'JOB-PROFILE'), cb.equal(root.get('stateKey'), 'NEW'))
}))

def freelancerApplications = entityService.findAll(Specifications.where({ Root root, CriteriaQuery query, CriteriaBuilder cb ->
    return cb.equal(root.get('typeKey'), 'APPLICATION')
}))

for (def freelancerApplication: freelancerApplications) {
    for (def link: freelancerApplication.getTargets()) {
        jobProfiles = jobProfiles.stream()
                .filter { profile -> profile.id != link.target.id }
                .collect()
    }
}

Map<String, Object> map = ["data": jobProfiles]
return map
