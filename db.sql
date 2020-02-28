CREATE TABLE public.events (
	issue varchar NOT NULL,
	"event" varchar NOT NULL
);

-- Permissions

ALTER TABLE public.events OWNER TO "user";
GRANT ALL ON TABLE public.events TO "user";

