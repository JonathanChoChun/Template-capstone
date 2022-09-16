truncate table venue cascade;
insert into venue (venue_name
        ,address
        ,city
        ,country
        ,postal_code
        ,longitude
        ,latitude
        ,timezone)
select distinct
VenueName
,VenueAddress
,VenueCity
,VenueCountry
,VenuePostalCode
,cast(VenueLongitude as float)
,cast(VenueLatitude as float)
,VenueTimeZone
from eventSamples;

-- select * from venue;

insert into attraction (attraction_name, attraction_type, url, image_url)
select distinct
    e.attractionname
    ,e.attractiontype
    ,e.attractionurl
    ,e.attractionimageurl
from
    eventSamples e;

-- select * from attraction;
truncate table events cascade;
insert into events (
                    event_name,
                    local_date,
                    local_time,
                    event_type,
                    image_url,
                    venue_id,
                    attraction_id,
                    is_active)

select distinct
    e.name
    ,cast(e.datestart as date)
    ,cast(e.datestart as time)
    ,e.typename
    ,e.attractionimageurl
    ,venue_id
    ,attraction_id
    ,true as is_active
from eventSamples e
join attraction a
    on a.attraction_name = e.attractionname
join venue v
     on v.venue_name = e.venuename
order by
    e.name