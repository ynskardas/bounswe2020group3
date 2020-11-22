from django.contrib import admin
from .models.example import Example

# Register your models here.
from .models.profile import Profile
from .models.project import Project
from .models.event import Event

admin.site.register(Example)
admin.site.register(Profile)
admin.site.register(Project)
admin.site.register(Event)
