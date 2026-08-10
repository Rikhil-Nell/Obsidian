When you combine several tools in order to provide a service, the service’s interface or API usually hides those implementation details from clients. Now you have essen‐ tially created a new, special-purpose data system 

from smaller, general-purpose com‐ ponents. Your composite data system may provide certain guarantees, e.g. that the cache will be correctly invalidated or updated on writes, 


so that outside clients see consistent results. You are now not only an application developer, but also a data sys‐ tem designer. If you are designing a data system or service, a lot of tricky questions arise. How do you ensure that the data remains correct and complete, even when things go wrong internally? How do you provide 


	consistently good performance to clients, even when parts of your system are degraded? How do you scale to handle an increase in load? What does a good API for the service look like?

but what is it that I am working towards? am I such a completionist? 