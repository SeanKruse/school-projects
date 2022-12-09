:- dynamic fact/1.
:- dynamic fact/2.
:- dynamic neg_fact/1.

is_true(Question) :-
  fact(Question)->true;
        neg_fact(Question)->false;
        (
              format('~w?~n',[Question]),
              Answer = read(yes),
              (
                    Answer ->
                           assert(fact(Question));
                           assert(neg_fact(Question)),false
              )
        )
.

membera(X, [X | _]).
membera(X, [_ | T]) :- membera(X, T).

is_option(Question, Options) :-
  (
    fact(Question, Options) ->
      true;
      format('~w?~n', [Question]),
      read(Answer),
      ( membera(Answer, Options) -> assert(fact(Question, Options)) )
  ).

disease(cholera) :- is_true("Have you been vomiting"),is_true("Do you have diarrhea"),is_true("Do you have leg cramps"),is_true("Are you thirsty").
disease(norovirus) :- is_true("Have you been vomiting"),is_true("Do you have diarrhea"),is_true("Do you have stomach cramps"),is_true("Has the vomiting been frequent and intense").
disease(e_coli) :- is_true("Have you been vomiting"),is_true("Do you have diarrhea"),is_true("Do you have stomach cramps"),is_true("Have you eaten ground beef recently").
disease(salmonella) :- is_true("Have you been vomiting"),is_true("Do you have diarrhea"),is_true("Do you have stomach pain"),is_true("Have you eaten undercooked food or used a unclean surface when preparing food").
disease(west_nile_virus) :- is_true("Have you been vomiting"),is_true("Do you have joint and muscle pain"),is_true("Do you have a diffused rash").

disease(bronchitis) :- is_true("Do you have chest pain"),is_true("Do you have a cough"),is_true("Do you have shortness of breath"),is_true("Are you producing mucus when you cough").
disease(pneumonia) :- is_true("Do you have chest pain"),is_true("Do you have a cough"),is_true("Do you have shortness of breath"),is_true("Do you have muscle aches").
disease(angina) :- is_true("Do you have chest pain"),is_true("Are you naseous"),is_true("Are you dizzy or lightheaded").

disease(smallpox) :- is_true("Do you have a rash"),is_true("Is the rash occuring all over the body"),is_true("Do you have severe back pain").
disease(zika_virus) :- is_true("Do you have a rash"),is_true("Is the rash occuring all over the body"),is_true("Is the rash consisting of tiny red bumps"),is_true("Do you have red eyes").
disease(syphilis) :- is_true("Do you have a rash"),is_true("Is the rash occuring all over the body"),is_true("Have you experienced weight loss"),is_true("Have you experienced hair loss").
disease(chickenpox) :- is_true("Do you have a rash"),is_true("Is the rash concentrated on your face, chest, and back"),is_true("Are the spots turning into blisters").
disease(measels)  :- is_true("Do you have a rash"),is_true("Is the rash concentrated on the forehead"),is_true("Do you have inflamed or watery eyes").
disease(roseola) :- is_true("Do you have a rash"),is_true("Is the rash occuring on the face and limbs"),is_true("Do you have a fever").
disease(lyme_disease) :- is_true("Do you have a rash"),is_true("Is the rash occuring in one location with a bullseye appearance"), is_true("Do you have a fatigue").

disease(influenza) :- is_true("Do you have shaking chills"),is_true("Are you frequently coughing"),is_true("Do you have a fever or headache").
disease(malaria) :- is_true("Do you have shaking chills"),is_true("Are you sweating profusely"),is_true("Do you have naseau and vomiting").

disease(common_cold) :- is_true("Do you have a sore throat"),is_true("Are your frequently sneezing"),is_true("Do you have watery eyes").
disease(strep_throat) :- is_true("Do you have a sore throat"),is_true("Is it painful to swallow"),is_true("Do you have swollen lymph nodes"),is_true("Are there white patches on your tonsils").
disease(tonsilitis) :- is_true("Do you have a sore throat"),is_true("Is it painful to swallow"),is_true("Do you have swollen tonsils").

disease(covid19) :- is_true("Have you lost your sense of taste or smell recently"),is_true("Do you have a fever or chills"),is_true("Do you have a sore throat or cough").

disease(rabies):-
  is_option('Please give us the symptom you are experiencing',[muscle_spasms,excessive_salivation,paralysis,mental_confusion])->
        true,
        retractall(fact(_,_));
              false,
              retractall(fact(_,_)).

begin :- (
    writeln("Welcome user to our Expert System on Sicknesses."),
    writeln("We are going to ask you some questions about your symptoms."),
    writeln("The questions will either be yes/no or choice questions."),
    writeln("Ready?")),

   read(yes)->
    (
             disease(A) -> format('I think your sickness is ~w.~n', [A]),
            (
              writeln("was the Expert System correct?"),
              Response=read(yes),
              Response->writeln("We hope this diagnosis helps you identify the potential issue, please consult a medical professional for confirmation and treatment."),
              writeln("Now exiting the Expert System. To try again type begin.");
              writeln("If you have diagnosed and confirmed your sickness please let our team know so that we can add the information to our system!"),
              writeln("Now exiting the Expert System. To try again type begin.")
             ),
            retractall(fact(_)),retractall(neg_fact(_));
                  writeln("Sorry it seems that this sickness is not in our system."),
                   writeln("Now exiting the Expert System. To try again type begin."),
                   retractall(fact(_)),retractall(neg_fact(_))
    );
   writeln("Bye!"),
   writeln("Now exiting the Expert System. To try again type begin.").