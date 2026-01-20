# Fields To Create

## Call Status

![[Pasted image 20260108203156.png]]

## Times Called
![[Pasted image 20260108230347.png]]



# Pipelines to Make

## Sales Pipeline
![[Pasted image 20260108230301.png]]

# Tags to Make

- ai start
- test
- qualified
- not qualified
- incomplete

# To Test

- Add Contact Tag to the triggers with a filter with a specific test tag.
- edit the facebook lead branch with an OR condition where in the new options workflow trigger is contact tag.
- Make a contact manually and assign the contact with the tag created.
- go to the wait timer and allow the user to pass through
- make sure your n8n webhook is disconnected from your http request node for making the call
- make sure the GHL workflow and n8n workflow are published and running