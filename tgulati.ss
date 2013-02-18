;Team: Tarun Gulati & Nasheed Moiz
;ID: tgulati & nmoiz
;Course: B552
;Assignment #3 -----SCROLL BELOW

;Question 1
;list called rules with rules encoded as a list in format described in assignment
(define *rules* '((high-fever-check ((symptom fever ?patient very-high)) ((symptom fever ?patient high))) ;If patient has v high fever,
                                                                                                       ;patient has fever
(cough-check ((disease ?patient whooping-cough)) ((symptom ?patient cough))) ;If patient has whoop cough, patient has cough

(rash-check ((disease ?patient poison-ivy)) ((symptom ?patient rash))) ;If patient has poison ivy, patient has rash

(flu-check ((symptom fever ?patient high)(symptom ?patient congestion)) ((disease ?patient flu))) ;if patient has h fever and congestion
                                                                                                  ;patient has flu

(poison-ivy-check((symptom ?patient rash)(not (symptom fever ?patient high))) ((disease ?patient poison-ivy)));if patient has rash and no
                                                                                                   ;high fever, patient has poison ivy

(whooping-cough-check ((symptom ?patient cough)(symptom fever ?patient very-high)) ((disease cough ?patient whooping))) ;if patient
                                                                             ;has cough and very high fever, patient has whooping cough

(health-check ((not (symptom ?patient cough)) (not(symptom ?patient cough)) (not(symptom ?patient rash)) ) ((status ?patient healthy)))
                                                                      ;if patient has no fever, no cough no rash, the patient is healthy

(infection-check ((status ?patient infected)(disease ?patient contagious) (contact-made ?patient ?person)) ((status ?person disease)))
              ;if patient has a contagious disease and makes contact with another person, then latter has disease too

(diagnosis-check ((said ?doctor (or (any ?patient disease) (status ?patient healthy)))) ((status ?patient disease)))
                  ;if a doctor says that a person has a disease or is healthy, then what doctor says is true

(doctor-check ((and(said ?person (or (any ?patient disease) (status ?patient healthy))) (and (not (any ?patient disease)) (not (status ?patient healthy))))) (not-a ?person doctor)))) ;if a person says that a patient has a disease or is healthy and it is not true, the former is not a doctor



;Question 2

(define *wm* '((symptom fever Ed very-high) ;Ed has very high fever
(symptom Ed cough)  ;Ed has a cough
(not (disease Alice poison-ivy))  ;Alice doesnt have poison ivy
(said Max (disease Alice poison-ivy)) ;Max says Alice has poisonivy
(said Grace (status Don healthy)) ;Grace said Don is healthy
(is a Grace doctor) ;Grace is a doctor
(disease whooping-cough contagious) ;Whooping cough is contagious
(contact-made Alice Ed))) ;Ed contacts Alice




;Question 3

(define (substitute bindings ls) ;substitute method which calls itself recursively with
(cond ((null? bindings) ls) ;(cdr bindings) and lst as paramenter in order to iterate through the many bindings
(else (substitute (cdr bindings) (subhelper (car bindings) ls)))))


(define (subhelper binding lst) ;helper method to substitute, takes one binding eg(?x Mary)
(cond ( (null? lst) '() ) ;and iterates through list substituting each instance as it finds it
((and (atom? lst) (eq? lst (car binding))) (cadr binding))
((and (atom? lst) (not (eq? lst (car binding)))) lst )
((pair? (car lst)) (cons (subhelper binding (subhelper binding (car lst))) (subhelper binding (cdr lst)) ))
((eq? (car binding) (car lst))  (cons (cadr binding) (subhelper binding (cdr lst))))
(else (cons (car lst) (subhelper binding (cdr lst))))))


;******************ASSIGNMENT 3 ****************************
;Question 1

(define var?   ;method provided in assignment description to check if parameter is a variable
  (lambda (obj)
    (and (symbol? obj)
       (char=? (string-ref (symbol->string obj) 0) #\?))))

(define (search-pat var pat) ;searches for a var in provided pattern, returns #t or #f
     (cond ((null? pat) #f)
      ((not (pair? pat)) (eq? var pat))   ;if pat not a pair, straight away tries to compare with var
      ((pair? (car pat)) (or (search-pat var (car pat)) (search-pat var (cdr pat)))) ;if first element of pat is a pair, then it checks inside it,
                                                                                     ;and makes recursive call to remainder of it
       (else (or (eq? var (car pat)) (search-pat var (cdr pat)))))) ;checks if either (car pat) is var we are looking for,
                                                                    ;or makes another recursive call on (cdr pat)


(define (unify-var var pat substitution) ;unify-var implemented as described in assignment. Makes use of substitute
                                         ;from assignment 2 and search-pat to check if a variable is in a pattern
  (cond ((not (eq? (substitute substitution var) var)) (unify (substitute substitution var) pat substitution)) ;if var has binding in substitution
                                                                                                               ;call unify
     ((eq?  (substitute substitution var) var) (if (search-pat var (substitute substitution pat)) #f (cons (list var pat) substitution))))) ;if var results in result from
                                                                                                       ;substitution, return #f else add new binding to substitution

(define (unify pat1 pat2 substitution) ;unify implemented as described in assignment
   (cond ((eq? pat1 pat2) substitution) ;
  ((var? pat1) (unify-var pat1 pat2 substitution))
   ((var? pat2) (unify-var pat2 pat1 substitution))
    ((or (atom? pat1)(atom? pat2)) #f)
     ((and (pair? pat1) (pair? pat2)) (if (unify (car pat1) (car pat2) substitution)  (unify (cdr pat1) (cdr pat2) (unify (car pat1) (car pat2) substitution)) #f))
                     ;Call unify on corresponding parts of pat1 and pat2. if #f results, return #f immediately.else a substitution results
      (else #f))) ;replace substitution with this and continue.


;Question 2

(define (match-antecedent wm antecedents substitutions) ;implementation of match-antecedent as described in assignment
    (matching-helper wm antecedents substitutions '()))

(define (matching-helper wm antecedent substitutions states)
    (if (null? wm) states (if (unify (car antecedent) (car wm) substitutions)
            (matching-helper (cdr wm) antecedent substitutions (cons (list (cdr antecedent) (unify (car antecedent) (car wm) substitutions)) states))
                  ;recursive call with states updated
            (matching-helper (cdr wm) antecedent substitutions states )))) ;recursive call to remainder of working memory


;******************************** ASSIGNMENT 4 **************************************

;Question 1
(define (iterator pats wm) ;a simple iterator function used to traverse through pats
  (cond ((null? pats) wm)
    (else (cond ((execute-helper (car pats) wm) (iterator (cdr pats) wm))
            (else (iterator (cdr pats) (cons (car pats) wm)))))))

(define (execute-helper subs wm)
  (cond ((eq? wm '()) #f)
    ((equal? subs (car wm)) #t)
    (else (not(equal? subs (car wm))) (execute-helper subs (cdr wm)))))  ;if substitute and a single assertion aren't equal then recursively call
                                                                         ;execute helper with the rest of the working memory for the same sustitution to check

(define (execute sub rhs wm empty-list)
  (cond ((eq? sub '()) empty-list)
    ((eq? rhs '()) empty-list)
    (else (cond ((eq? (execute-helper (substitute sub (car rhs)) wm) #t) (execute1 sub (cdr rhs) wm empty-list))
            ;if execute-helper returns true then call execute with the rest of the rhs, i.e. the new consequent is already present in the working memory
              (else (execute sub (cdr rhs) wm (cons (substitute sub (car rhs)) empty-list)))))))
                   ;if execute-helper returns false then call execute with the rest of the rhs,
                   ;but also append the new consequent to empty-list as it isn't present in the working memory
;Question 2
(define match-rule
  (lambda (name lhs rhs wm)
    (display "\n\nAttempting to match rule: ")(display name)(newline)
    (letrec ((mr-helper
              (lambda (queue new-wm)
                (cond ((null? queue) new-wm) ;return new-wm if empty queue
                  (else (cond
                            ((null? (caar queue)) (mr-helper (cdr queue) (iterator (execute (cadr (car queue)) rhs new-wm '()) new-wm)))
                ;If state1 (caar queue) has no antecedents, call execute on rhs using the substitution in state

                            (else (if (null? (match-antecedent wm (caar queue) (cadr (car queue)))) (mr-helper (cdr queue) new-wm)
                                        (mr-helper (append (match-antecedent wm (caar queue) (cadr (car queue))) (cdr queue)) new-wm)))))))))
        ;call mr-helper on the rest of the queue, appending whatever new WM assertions to queue

        (if (null? (mr-helper2 (mr-helper (match-antecedent wm lhs '()) '()) wm '())) (display "Failing \n")
                   (begin (display "Match Succeeds \nAdding assertions to WM:\n") (display  (mr-helper2 (mr-helper (match-antecedent wm lhs '()) '()) wm '())  )))
                                                                                                              ;letrec body
                                   ;if secondary helper method returns an empty list, that means nothing in existing wm matched with a rule, so display failing
        (mr-helper2 (mr-helper (match-antecedent wm lhs '()) '()) wm '()) ))) ;else return what secondary helper returned


(define (mr-helper2 new-wm wm empty-list) ;secondary helper method to help construct the new pat that results from applying match-rule
             (cond ((null? new-wm) empty-list);check if empty
                  (else (cond ((execute-helper (car new-wm) wm) (mr-helper2 (cdr new-wm) wm empty-list))
                     (else (mr-helper2 (cdr new-wm) wm (cons (car new-wm) empty-list)) ) ) ) ) )
;if first element of new-wm is already in wm (check using execute-helper, then just recurse without doing anything,
;else recurse with cons-ed version of new-wm to build up


;Question 3
(define (match-rules rules wm) ;match-rules repeatedly calls match-rule, uses its helper function to create and
  (match-rules-helper rules wm '())) ;return a list of patterns which resulted from calls to match-rule

(define (match-rules-helper rules wm empty-list)
  (cond ((eq? rules '()) empty-list)
    (else (match-rules-helper (cdr rules) wm (iterator (match-rule (car (car rules)) (cadr (car rules)) (caddr (car rules)) wm) empty-list)))))

;Question 4
(define (run-ps rules wm)  ;main like method that calls match-rules to start production system,
  (display "WORKING MEMORY:\n")(display wm)(newline)(newline)
  (cond ((null? rules) wm)
    (else (cond ((eq? (match-rules rules wm ) '()) (match-rules (cdr rules) wm ))
            (else (match-rules (cdr rules) (cons (match-rules (car rules) wm ) wm) ))))))
 ;recurses on remainder of rules to update working memory