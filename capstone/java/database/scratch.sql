select * from app_user;
select * from person;

update app_user set role='admin' where user_name='rob';
update app_user set role='standard' where user_name='rob';

select
    m.*,
    p.person_name as director_name
from
    movie m
    left join person p
        on p.person_id = m.director_id
where
        (
          m.title ilike :search
          or
          m.overview ilike :search
        )
        and
        (
          m.length_minutes between :minLength and :maxLength
        );


--
-- select
--         p.*
--     from
--     person p
--     join movie_actor ma
--         on p.person_id = ma.actor_id;
-- where
--     p.person_name ilike :name
-- order by p.person_name



select
    m.*,
    p.person_name as director_name
from
    movie m
        left join person p
                  on p.person_id = m.director_id
where
    m.movie_id =  1;



update app_user
set
    first_name=:first_name,
    last_name=:last_name,
    role=:role
where
    id=:id;

update app_user
set
    password = :password,
    salt = :salt
where
    user_name = :username;



update movie
    set
        overview = :overview
    where
        movie_id = :id;


select
    i.*
from
    image i;

select * from app_user;

update app_user set profile_image_id=null where id=4;


drop table if exists app_user;
CREATE TABLE app_user (id SERIAL NOT NULL, user_name CHARACTER VARYING(32) NOT NULL, password CHARACTER VARYING(32) NOT NULL, first_name CHARACTER VARYING(32) NOT NULL, last_name CHARACTER VARYING(32) NOT NULL, role CHARACTER VARYING(32) NOT NULL, salt CHARACTER VARYING(255) NOT NULL, profile_image_id bigint ,active BOOLEAN, PRIMARY KEY (id), UNIQUE (user_name));
INSERT INTO app_user (user_name, password, first_name, last_name, role, salt, active) VALUES ('test', '6PVA+4+F2VUDxB4+kdrlJQ==', 'david', 'vickars', 'standard', 'jP72xemDanXau65hNc+VruJyG1FGLQjU9JzoVFIn/CLJeT5x/9AkcrIe6cNAciJJiC5ERFy1bZU5gTGdJ+U1eEyGt5mVR8qUN/7KVL7oZyilkdtel11dCl582tWC1qJLmf+SvxITupsbFOGUqkTpW3PJHtBgRoS0HEg4C4SR3Oo=', true);
INSERT INTO app_user (user_name, password, first_name, last_name, role, salt, active) VALUES ('rob', 'YmJH+WL8utqsvx2q6aBHZg==', 'rob', 'stewart', 'admin', '5ySh1gZQdL25Zl9CYjLWTt32UC4eBV06IKAE58xW2s29DzlhLdXOTGG3q189PCWjBMzAkTSJ+bfzF/3zwAlmUYFRcHtmzLDwallFRWTUQHJVxszLkLx6u+Ph2eVEwEaLPxOegvOehnx63hjl0qGTm3OA/M4Wwo9o0J2JPIuxCkY=', true);


select * from eventSamples;

